package oracle.project.AssetManagement.uiview;

import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Apr 20 22:58:16 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class YearlyAnalyticsBookLVVOImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public YearlyAnalyticsBookLVVOImpl() {
    }

    /**
     * Returns the variable value for p_user.
     * @return variable value for p_user
     */
    public String getp_user() {
        return (String)ensureVariableManager().getVariableValue("p_user");
    }

    /**
     * Sets <code>value</code> for variable p_user.
     * @param value value to bind as p_user
     */
    public void setp_user(String value) {
        ensureVariableManager().setVariableValue("p_user", value);
    }
}