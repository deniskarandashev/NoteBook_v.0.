<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>

    <div class="alert alert-primary" role="alert">
        <@spring.message "listUsers"/>
    </div>
    <br>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"><@spring.message "name"/></th>
            <th scope="col"><@spring.message "role"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <#list user.roles as role>
                <#if role != "ADMIN">
                    <tr>
                        <td scope="col">${user.username}</td>
                        <td scope="col"><button type="button" class="btn btn-success"><@spring.message "user"/></button></td>
                        <td scope="col"><a href="/user/${user.id}"><@spring.message "edit"/></a></td>
                    </tr>
                </#if>
                <#if role == "ADMIN">
                    <tr>
                        <td scope="col">${user.username}</td>
                        <td scope="col"><button type="button" class="btn btn-danger"><@spring.message "adminpanel"/></button></td>
                        <td scope="col"><a href="/user/${user.id}"><@spring.message "edit"/></a></td>
                    </tr>
                </#if>
            </#list>
        </#list>
        </tbody>
    </table>

</@c.page>
