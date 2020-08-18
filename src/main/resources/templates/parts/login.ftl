<#macro login path isRegisterForm>
    <#import "/spring.ftl" as spring/>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message "login"/></label>
            <div class="col-sm-6">
                <input type="text" name="username" minlength="3" maxlength="10"
                       value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="<@spring.message "login"/>" required/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message "password"/></label>
            <div class="col-sm-6">
                <input type="password" name="password" minlength="3" maxlength="10"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="<@spring.message "password"/>" required/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><@spring.message "password"/></label>
            <div class="col-sm-6">
                <input type="password" name="password2" minlength="3" maxlength="10"
                       class="form-control ${(password2Error??)?string('is-invalid', '')}"
                       placeholder="<@spring.message "password"/>" required/>
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
                </#if>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"></label>
                    <div class="col-sm-6 col-">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <br>
                        <button class="btn btn-primary"
                                type="submit"><#if isRegisterForm><@spring.message "adduser"/><#else><@spring.message "enter"/></#if></button>
                        <br>
                        <br>
                        <#if !isRegisterForm><a href="/registration"><@spring.message "adduser"/></a></#if>
                    </div>
                </div>
            </div>
        </div>
        <#--        <div class="form-group row">-->
        <#--            <label class="col-sm-2 col-form-label">Email:</label>-->
        <#--            <div class="col-sm-6">-->
        <#--                <input type="email" name="email" value="<#if user??>${user.email}</#if>"-->
        <#--                       class="form-control ${(emailError??)?string('is-invalid', '')}"-->
        <#--                       placeholder="some@some.com" />-->
        <#--                <#if emailError??>-->
        <#--                    <div class="invalid-feedback">-->
        <#--                        ${emailError}-->
        <#--                    </div>-->
        <#--                </#if>-->
        <#--            </div>-->
        <#--        </div>-->
        <#--        <div class="col-sm-6">-->
        <#--            <div class="g-recaptcha" data-sitekey="6LduQVoUAAAAAD8hypySNroht_6UnzhoQRV3QIWc"></div>-->
        <#--            <#if captchaError??>-->
        <#--                <div class="alert alert-danger" role="alert">-->
        <#--                    ${captchaError}-->
        <#--                </div>-->
        <#--            </#if>-->
        <#--        </div>-->

    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><@spring.message "logout"/></button>
    </form>
</#macro>
