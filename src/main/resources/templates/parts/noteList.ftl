<#include "security.ftl">
<#import "/spring.ftl" as spring/>

<br>
<div class="alert alert-primary">
    <div class="card-columns">
        <#list notes as note>
            <#if (note.author.id == currentUserId && note.isVisible() == true)>
<#--                <#if note.isVisible() == true>-->
                    <div class="card my-3">
                        <div class="m-2">
                            <#include "convert.ftl">
                            <strong><i>${note.title}</i></strong><br/>
                            <span>${note.text}</span><br>
                            <div style="color: darkolivegreen">
                                <small>
                                    <i>
                                        <span><@spring.message "author"/> ${user.username}</span>
                                        <#if note.coauthor?if_exists != "0">
                                            <br><span><@spring.message "coauthor"/> ${note.coauthor?if_exists}</span>
                                        </#if>
                                        <#--                                    <@spring.message "coauthButton"/> ${coauthor?ifExists}<br>-->
                                        <#if note.isRestored() == false>
                                            <br>
                                            <span><@spring.message "created"/> ${note.date?date} <@spring.message "at"/> ${note.date?time}</span>
                                            <br>
                                            <#if note.date?datetime != note.lastEditDate?datetime>
                                                <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>
                                            </#if>
                                        </#if>

                                        <#if note.isRestored() == true>
                                            <br>
                                            <span style="color: blueviolet"><@spring.message "restored"/> ${note.date?date} <@spring.message "at"/> ${note.date?time}</span>
                                        </#if>

                                        <#--                                    <a href="/toXml/${note.author.id}?note=${note.id}">-->
                                        <#--                                        XML-->
                                        <#--                                    </a>-->
                                    </i>
                                </small>
                            </div>
                        </div>

                        <div class="card-footer text-muted" style="align-content: center">
                            <#if note.author.id == currentUserId>
                                <a class="btn btn-success" href="/user-notes/${note.author.id}?note=${note.id}">
                                    <@spring.message "edit"/>
                                </a>
                                <a class="btn btn-warning" href="/delete/${note.author.id}?note=${note.id}">
                                    <@spring.message "del"/>
                                </a>
                                <#if note.date?datetime != note.lastEditDate?datetime>
                                    <a class="btn btn-info" href="/ed-not/${note.author.id}?note=${note.id}">
                                        <@spring.message "history"/>
                                    </a>
                                </#if>
                            </#if>
                        </div>
                    </div>
<#--                <#else>-->
<#--                    <@spring.message "nonotes"/>-->
<#--                </#if>-->
            </#if>
        </#list>
    </div>
</div>


<#--<#list users as user>-->
<#--    <#if user.coauthor == name>-->
<div class="alert alert-success">
<div class="card-columns">
    <#list notes as note>
        <#if note.coauthor?if_exists == user.username?if_exists>
            <#if note.isVisible() == true>

                <div class="card my-3">

                    <div class="m-2">
                        <#include "convert.ftl">
                        <strong><i>${note.title}</i></strong><br/>
                        <span>${note.text}</span><br>

                        <div style="color: darkolivegreen">
                            <small>
                                <i>
                                    <span><@spring.message "author"/> ${note.author.username}</span><br>
                                    <span><@spring.message "coauthor"/> ${note.coauthor?if_exists}</span>
                                    <#if note.isRestored() == false>
                                    <#--                                    <@spring.message "coauthButton"/> ${coauthor?ifExists}<br>-->
                                    <br><span><@spring.message "created" /> ${note.date?date?if_exists} <@spring.message "at"/> ${note.date?if_exists}</span><br>
                                    <#if note.date?datetime != note.lastEditDate?datetime>
                                        <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>
                                    </#if>

                            <#--                        </div>-->
                            </#if>
                            <#if note.isRestored() == true>
                                <br>
                                <span style="color: blueviolet"><@spring.message "restored"/> ${note.date?date} <@spring.message "at"/> ${note.date?time}</span>
                            </#if>
                                </i>
                            </small>
                        </div>
                    </div>

                    <div class="card-footer text-muted" style="align-content: center">
                        <#--                            <#if note.author.id == currentUserId>-->
                        <a class="btn btn-success" href="/user-notes/${note.coauthorId?if_exists}?note=${note.id}">
                            <@spring.message "edit"/>
                        </a>
                        <a class="btn btn-warning" href="/delete/${note.author.id}?note=${note.id}">
                            <@spring.message "del"/>
                        </a>
                        <#if note.date?datetime != note.lastEditDate?datetime>
                            <a class="btn btn-info" href="/ed-not/${note.author.id}?note=${note.id}">
                                <@spring.message "history"/>
                            </a>
                        </#if>
                        <#--                            </#if>-->
                    </div>
                </div>
            </#if>

        <#--    <#else >-->
        </#if>
    </#list>
</div>
</div>
<#--    </#if>-->
<#--</#list>-->


<#--<#list users as user>-->
<#--    <#if user.coauthor == name>-->
<#--        -->
<#--        <div class="alert alert-warning" role="alert">-->
<#--            <span><@spring.message "rights"/> ${user.username}</span><br>-->
<#--        </div>-->
<#--        <div class="card-columns">-->
<#--            <#list notes as note>-->
<#--                <#if note.isVisible() == true>-->
<#--                    <div class="card my-3">-->

<#--                        <div class="m-2">-->
<#--                            <strong><i>${note.title}</i></strong><br/>-->
<#--                            &lt;#&ndash;                <span>${note.date}</span><br>&ndash;&gt;-->
<#--                            <span>${note.text}</span><br>-->
<#--                            <div style="color: darkolivegreen"><small><i>-->
<#--                                        <span><@spring.message "author"/>${note.author.username}</span><br>-->
<#--                                        &lt;#&ndash;                                    <@spring.message "coauthButton"/> ${coauthor?ifExists}<br>&ndash;&gt;-->
<#--                                        <span><@spring.message "created"/> ${note.date?date} <@spring.message "at"/> ${note.date?time}</span><br>-->
<#--                                        <#if note.date?datetime != note.lastEditDate?datetime>-->
<#--                                            <span><@spring.message "edited"/> ${note.lastEditDate?date} <@spring.message "at"/> ${note.lastEditDate?time}</span>-->
<#--                                        </#if>-->
<#--                                    </i></small></div>-->
<#--                        </div>-->

<#--                        <div class="card-footer text-muted" style="align-content: center">-->
<#--&lt;#&ndash;                            <#if note.author.id == currentUserId>&ndash;&gt;-->
<#--                                <a class="btn btn-success" href="/user-notes/${note.author.id}?note=${note.id}">-->
<#--                                    <@spring.message "edit"/>-->
<#--                                </a>-->
<#--                                <a class="btn btn-warning" href="/delete/${note.author.id}?note=${note.id}">-->
<#--                                    <@spring.message "del"/>-->
<#--                                </a>-->
<#--                                <#if note.date?datetime != note.lastEditDate?datetime>-->
<#--                                    <a class="btn btn-info" href="/ed-not/${note.author.id}?note=${note.id}">-->
<#--                                        <@spring.message "history"/>-->
<#--                                    </a>-->
<#--                                </#if>-->
<#--&lt;#&ndash;                            </#if>&ndash;&gt;-->
<#--                        </div>-->
<#--                    </div>-->
<#--                </#if>-->
<#--            </#list>-->
<#--        </div>-->
<#--    <#else >-->
<#--</#if>-->
<#--   -->
<#--</#list>-->
