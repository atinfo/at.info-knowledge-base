from SeleniumWrapper import SeleniumWrapper as wrapper

locators = {
    "email": "id=Email",
    "password": "id=Password",
    "sign in": "id=submit",
    "error messages": "css=.validation-summary-errors li"
}

class SignInPage(object):
    def set_sign_in_email_as(self, email):
        se = wrapper().connection
        se.type(locators["email"], email)

    def set_sign_in_password_as(self, password):
        se = wrapper().connection
        se.type(locators["password"], password)

    def submit_sign_in_credentials(self, success=False):
        se = wrapper().connection
        se.click(locators["sign in"])
        se.wait_for_page_to_load("20000")

    def sign_in_error_message_should_be(self, message):
        se = wrapper().connection
        assert se.get_text(locators['error messages']) == message