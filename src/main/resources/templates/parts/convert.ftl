<#include "security.ftl">
<#import "/spring.ftl" as spring/>

<#--<div class="dropdown">-->
<div class="dropdown" style="left: 85%">
    <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        &#x2193;
    </a>
<form action="/toXml" method="get">
    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <a class="dropdown-item" href="/toXml/${note.author.id}?note=${note.id}"><@spring.message "saveas"/> XML</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/toJSON/${note.author.id}?note=${note.id}"><@spring.message "saveas"/> JSON</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/toDoc/${note.author.id}?note=${note.id}"><@spring.message "saveas"/> DOCX</a>
    </div>
</form>
</div>
