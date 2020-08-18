<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring/>
<#include "parts/security.ftl">

<@c.page>
    <div class="alert alert-success" role="alert">
        <@spring.message "redactionsFL"/>
    </div>
    <div class="card-columns">
        <#list notes as note>
            <#if note.isVisible() == true>
                <div class="card my-3">

                    <div class="m-2">
                        <strong><i>${note.title}</i></strong><br/>
                        <span>${note.text}</span><br>
                        <div style="color: darkolivegreen"><small><i>
                                    <#if note.date?datetime != note.lastEditDate?datetime>
                                        <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>
                                    </#if>
                                </i></small></div>
                    </div>
                    <div class="card-footer text-muted" style="color: lightskyblue">
                        <@spring.message "redLast"/>
                    </div>

                </div>
            </#if>
            <#if note.redaction == 1>
                <div class="card my-3">

                    <div class="m-2">
                        <strong><i>${note.title}</i></strong><br/>
                        <#--                <span>${note.date}</span><br>-->
                        <span>${note.text}</span><br>
                        <div style="color: darkolivegreen"><small><i>
                                    <span><@spring.message "created"/> ${note.date?date} <@spring.message "at"/> ${note.date?time}</span><br>
                                    <#if note.date?datetime != note.lastEditDate?datetime>
                                        <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>
                                    </#if>
                                </i></small></div>
                    </div>
                    <div class="card-footer text-muted" style="color: lightgreen">
                        <a><@spring.message "redFirst"/></a>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
<br>
    <#if (note.redaction > 2)>
    <div class="alert alert-success" role="alert">
        <@spring.message "redactionsOther"/>
    </div>
    <div class="card-columns" value="${note.noteGroup?ifExists}">
        <#list notes as note>
            <#if note.isVisible() == false>
                <#if note.redaction != 1>
                    <div class="card my-3">

                        <div class="m-2">
                            <strong><i>${note.title}</i></strong><br/>
                            <#--                <span>${note.date}</span><br>-->
                            <span>${note.text}</span><br>
                            <div style="color: darkolivegreen"><small><i>
                                        <#if note.date?datetime != note.lastEditDate?datetime>
                                            <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>
                                        </#if>
                                    </i></small></div>
                        </div>
                    </div>
                </#if>
            </#if>
        </#list>
    </div>
    </#if>
</@c.page>
