import nl.of.catchplus.PermissionService

/**

 * Generated by the Shiro plugin. This filters class protects all URLs
 * via access control by convention.
 */
class ShiroSecurityFilters {

    PermissionService permissionService

    def filters = {
        all(uri: "/**") {
            before = {

                //System.out.println(request.getRemoteAddr());
                //System.out.println(request.getUserPrincipal());
                //System.out.println(request.requestURI);

                // Ignore direct views (e.g. the default main index page).
                if (!controllerName | controllerName=='jaxrs' | (request.getRemoteAddr()=='127.0.0.1' && actionName=='show' && !request.getUserPrincipal() ) ) return true

                if(controllerName=='account' && (actionName=='activate' || actionName=='password' || actionName=='forgotpassword'))
                    return true

                if(controllerName=='storedFile' && (actionName=='render' || actionName=='download' ))
                {
                    return true
                }


/*
                if(controllerName=='collection' && (actionName.substring(0,4)=='test' || actionName=='password' ))
                    return true
*/

                // Access control by convention.
                accessControl()
            }
        }
    }
}
