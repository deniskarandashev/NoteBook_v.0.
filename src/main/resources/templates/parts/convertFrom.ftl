<#include "security.ftl">
<#import "/spring.ftl" as spring/>

<#--<div class="dropdown">-->
<div class="dropdown">
    <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        &#x2191;
    </a>

<form enctype="multipart/form-data" action="/fromXml" method="get">
    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <a class="dropdown-item" href="/fromXml/"><@spring.message "convertfrom"/> XML</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="/fromJSON"><@spring.message "convertfrom"/> JSON</a>
    </div>
</form>
</div>

<#--<form enctype="multipart/form-data" method="post" action="/uploadFile">-->
<#--    <input type="file" name="file">-->
<#--</form>-->