package stepdefs

import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks
import services.CreateAccountService

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

Given(~"Im on neo account creation page") { ->
    CreateAccountService.instance.getHome();
    CreateAccountService.instance.selectCreateAccount();
}

When(~"I set a/an (.*) and (.*) and create account button is pushed") { String emailField,passField ->
    CreateAccountService.instance.setMail(emailField);
    CreateAccountService.instance.setPass(passField)
    CreateAccountService.instance.commitForm();
}

Then(~"(.*) error is shown") { String errorType ->
    CreateAccountService.instance.checkError(errorType);
}

When (~"I set an (.*)") { String captcha ->
    CreateAccountService.instance.setInvalidCaptcha()
}


After() { scenario ->
    CreateAccountService.instance.closeDriver()
}