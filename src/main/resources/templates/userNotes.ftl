<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/noteEdit.ftl" />
        <#include "parts/noteList.ftl" />
    </#if>
</@c.page>
