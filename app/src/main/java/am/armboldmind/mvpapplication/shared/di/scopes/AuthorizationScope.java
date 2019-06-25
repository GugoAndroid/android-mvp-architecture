package am.armboldmind.mvpapplication.shared.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface AuthorizationScope {
}