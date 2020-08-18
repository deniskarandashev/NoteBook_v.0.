
<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>

<@c.page>

<#--    <#if isCurrentUser></#if>-->
<#--<div class="form-row">-->
<#--    <div class="form-group col-md-6">-->
<#--        <form method="get" action="/main" class="form-inline">-->
<#--            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по заголовку">-->
<#--            <button type="submit" class="btn btn-primary ml-2">Искать!</button>-->
<#--        </form>-->
<#--    </div>-->
<#--</div>-->


<#include "parts/noteEdit.ftl" />

<#include "parts/noteList.ftl" />


</@c.page>
