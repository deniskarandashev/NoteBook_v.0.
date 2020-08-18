<#import "/spring.ftl" as spring/>

<#--<head>-->
    <meta charset="UTF-8">
<#--    <title>Title</title>-->
<#--</head>-->
<#--<body>-->
<#--<p th:text=""></p>-->
<#--#{info}-->
<#--<span th:text="#{lang.change}" xmlns:th="http://www.w3.org/1999/xhtml"></span>:-->
<#--    <select id="locales">-->
<#--    <option value=""></option>-->
<#--    <option value="en" th:text="#{lang.en}"></option>-->
<#--    <option value="ru" th:text="#{lang.ru}"></option>-->
<#--    </select>-->
<span style="float: right">
    <a href="?lang=en">en</a>
    <a href="?lang=ru">ru</a>
    </span>
<@spring.message "info"/>
<@spring.message "login"/>
<#--<div>-->
<#--<a th:href="@{'?locale=en'}">En</a>-->
<#--&lt;#&ndash;<a th:href="@{'?locale=ru'}">Ru</a>&ndash;&gt;-->
<#--<html lang="${locale.language!"en"}">-->
<#--<html lang="${locale.language!"ru"}">-->
<#--</div>-->

<#--<@spring.checkSelected "en"/>-->
<#--<div  class="navbar-text mr-3">-->
<#--    <form method="post">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <button class="btn btn-secondary" type="submit">-->
<#--            <a th:href="@{'?locale=ru'}">Ru</a>-->
<#--&lt;#&ndash;            <a th:href="@{/main?lang=ru}">Ru</a>&ndash;&gt;-->
<#--        </button>-->
<#--    </form>-->
<#--</div>-->
<#--<div  class="navbar-text mr-3">-->
<#--    <form method="post">-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--        <button class="btn btn-secondary" type="submit">-->
<#--            <a th:href="@{'?locale=en'}">En</a>-->
<#--&lt;#&ndash;            <a th:href="@{/main?lang=en}">En</a>&ndash;&gt;-->
<#--        </button>-->
<#--    </form>-->
<#--</div>-->


<#--</body>-->
<#--</html>-->