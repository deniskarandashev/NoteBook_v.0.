<#include "security.ftl">
<#import "/spring.ftl" as spring/>
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">NoteBook</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <#--                <a class="nav-link" href="/"><spring:resource code="infoNB"/></a>-->
                <a class="nav-link" href="/"><@spring.message "info"/></a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/main"><@spring.message "mynotes"/></a>
                </li>
            <#--                            <li class="nav-item">-->
            <#--                                <a class="nav-link" href="/user-notes/${currentUserId}">Мои заметки</a>-->
            <#--            &lt;#&ndash;                <a class="nav-link" href="/user-notes/${currentUserId}">Мои заметки</a>&ndash;&gt;-->
            <#--                            </li>-->
            </#if>

            <#--            <#if user??>-->
            <#--                <li class="nav-item">-->
            <#--                    <a class="nav-link" href="/user">Соавторы</a>-->
            <#--                </li>-->
            <#--            </#if>-->
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile"><@spring.message "profile"/></a>
                </li>
            </#if>
<#--            <#if isAdmin>-->
<#--                <li class="nav-item">-->
<#--                    <a class="btn btn-danger" href="/user"><@spring.message "adminpanel"/></a>-->
<#--                </li>-->
<#--            </#if>-->
        </ul>

        <#if user??>
        <div class="navbar-text mr-3">
            <form>
                <button class="btn btn-warning">${name?if_exists}</button>
            </form>
        </div>
        </#if>
<#--        <div class="navbar-text mr-3">-->
<#--            <form>-->
<#--                <button class="btn btn-danger" ><@spring.message "coauthButton"/>: ${coauthor?if_exists}</button>-->
<#--            </form>-->
<#--        </div>-->

        <div class="button-bar">
<#--            <#if !user??>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link" href="/login"><@spring.message "autorization"/></a>-->
<#--                </li>-->
<#--            </#if>-->
            <#if !user??>
                <form action="/login" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" type="submit"><@spring.message "autorization"/></button>
                </form>
            </#if>
            <#if user??>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" type="submit"><@spring.message "logout"/></button>
                </form>
            </#if>
        </div>
    </div>
</nav>
