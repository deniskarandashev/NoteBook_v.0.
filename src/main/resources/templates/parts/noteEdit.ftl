<#import "/spring.ftl" as spring/>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    <@spring.message "regTable"/>
</a>
<br>

<div class="collapse <#if note??>show</#if>" id="collapseExample">
    <#include "convertFrom.ftl">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <#--                <#include "convert.ftl">-->
                <input type="text" class="form-control"
                       value="<#if note??>${note.title}</#if>" name="title" placeholder="<@spring.message "title"/>"
                       required>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if note??>${note.text}</#if>" name="text" placeholder="<@spring.message "text"/>"
                       rows="3" required/>
                <#--                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>-->
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if note??>${note.id}</#if>"/>

            <div class="form-group">
                <button type="submit" class="btn btn-primary"><@spring.message "save"/></button>
            </div>


        </form>
    </div>
</div>

