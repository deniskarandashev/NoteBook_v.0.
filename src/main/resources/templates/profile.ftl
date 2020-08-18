<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <div class="alert alert-info">
    <h5>${username}</h5>
        <div>
            <@spring.message "regDate"/> ${regDate?date} в ${regDate?time}<br><br>
        </div>
        ${message?ifExists}

        <form method="post">
            <div class="form-group row">
                <div class="col-sm-6">
                    <input type="text" name="coauthor" class="form-control"
                           placeholder="<@spring.message "coauthButton"/>"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit"><@spring.message "save"/></button>
            <#if coauthor != "0">
                <button class="btn btn-warning" type="submit"><@spring.message "coauthButton"/>: ${coauthor}
                    | <@spring.message "del"/></button>
            </#if>
        </form>
    </div>
<#--    <div class="alert alert-success">-->
<#--        <#list cousers?ifExists as couser>-->
<#--        <form method="post">-->
<#--            <div class="form-group row">-->
<#--                <div class="col-sm-6">-->
<#--                    <input type="text" name="coreader" class="form-control"-->
<#--                           placeholder="<@spring.message "coreader"/>"/>-->
<#--                </div>-->
<#--            </div>-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button class="btn btn-primary" type="submit"><@spring.message "save"/></button>-->
<#--            <#if coreader != "0">-->
<#--            <button class="btn btn-warning" type="submit"><@spring.message "coreader"/>: ${coreader}-->
<#--                | <@spring.message "del"/>-->
<#--                </#if>-->
<#--        </form>-->
<#--        </#list>-->
<#--    </div>-->
</@c.page>
<#--profile/addcoreader-->