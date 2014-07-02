package org.openntf.bstemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import lotus.domino.Document;

import com.ibm.xsp.extlib.util.ExtLibUtil;
import com.ibm.xsp.model.domino.wrapped.DominoDocument;

public class Navigation implements Serializable {

	private static final long serialVersionUID = 8031965383531253276L;
	private final List<Page> navigation;

	@SuppressWarnings("static-access")
	public Navigation() {
		this.navigation = new ArrayList<Page>();

		SysInfo sysinfo = (SysInfo) ExtLibUtil.resolveVariable(FacesContext.getCurrentInstance(),
				"sysinfo");

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		if (req.getRequestURI().indexOf("userprofile.xsp") != -1
				&& sysinfo.hasOption(sysinfo.DBACL_CREATE_DOCS)) {
			try {
				DominoDocument xspdoc = (DominoDocument) ExtLibUtil.resolveVariable(FacesContext
						.getCurrentInstance(), "currentDocument");
				Document doc = xspdoc.getDocument();
				String userId = "";

				userId = doc.getItemValueString("userId");

				this.navigation.add(new Page("Meinem Netzwerk hinzufügen", "add.xsp?id=" + userId,
						"fa fa-flag", false));
			} catch (Exception e) {
			}

		}

		this.navigation.add(new Page("Search", "search.xsp", "fa fa-search", false));
		this.navigation.add(new Page("All profiles", "all.xsp", "fa fa-group", false));
	}

	public List<Page> getNavigation() {
		return navigation;
	}
}
