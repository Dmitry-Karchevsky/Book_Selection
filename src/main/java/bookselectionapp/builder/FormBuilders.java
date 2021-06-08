package bookselectionapp.builder;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FormBuilders {

    public static class UserFormBuilder {
        private String email = "email";
        private String password = "password";
        private String name = "name";
        private String language = "language";
        private Integer age  = 20;

        public UserFormBuilder() {
        }
    }

    public static FormBuilders.UserFormBuilder defaultUserForm() {
        return new FormBuilders.UserFormBuilder();
    }
}
