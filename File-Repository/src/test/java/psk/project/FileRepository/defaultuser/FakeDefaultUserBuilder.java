package psk.project.FileRepository.defaultuser;

import psk.project.FileRepository.defaultuser.entity.DefaultUser;

import java.util.UUID;

import static psk.project.FileRepository.RandomUtilTest.randomString;
import static psk.project.FileRepository.RandomUtilTest.randomUUID;

public class FakeDefaultUserBuilder extends DefaultUser {

    public FakeDefaultUserBuilder() {
        setDefaultUserID(randomUUID());
        setName(randomString(6));
        setSurname(randomString(6));
        setLogin(randomString(6));
        setPassword(randomString(6));
        setEmail(randomString(6));
    }

    public FakeDefaultUserBuilder withId(UUID id){
        setDefaultUserID(id);
        return this;
    }

    public FakeDefaultUserBuilder withName(String name) {
        setName(name);
        return this;
    }

    public FakeDefaultUserBuilder withSurname(String surname) {
        setSurname(surname);
        return this;
    }

    public FakeDefaultUserBuilder withLogin(String login) {
        setLogin(login);
        return this;
    }

    public FakeDefaultUserBuilder withPassword(String password) {
        setPassword(password);
        return this;
    }

    public FakeDefaultUserBuilder withEmail(String email) {
        setEmail(email);
        return this;
    }
}
