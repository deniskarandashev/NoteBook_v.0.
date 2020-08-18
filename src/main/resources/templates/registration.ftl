<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring/>

<@c.page>
    <div class="mb-1"><@spring.message "adduser"/></div>
    <br>
    <@l.login "/registration" true />
    <br>
    <div style="color: crimson">${message?ifExists}</div>
</@c.page>