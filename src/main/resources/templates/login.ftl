<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring/>

<@c.page>
<#--<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>-->
<#--    <div class="alert alert-danger" role="alert">-->
<#--        ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}-->
<#--    </div>-->
<#--</#if>-->
<#--    <div>-->
<#--        <form method="post">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button class="btn btn-primary" type="submit">-->
<#--                <a href="?lang=ru">Ru</a>-->
<#--            </button>-->
<#--        </form>-->
<#--    </div>-->
<#--    <div>-->
<#--        <form method="post">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--            <button class="btn btn-primary" type="submit">-->
<#--                <a href="?lang=en">En</a>-->
<#--            </button>-->
<#--        </form>-->
<#--    </div>-->


    <#if message??>
    <div class="alert alert-${messageType}" role="alert">
        ${message}
    </div>
</#if>
<@l.login "/login" false/>
</@c.page>
