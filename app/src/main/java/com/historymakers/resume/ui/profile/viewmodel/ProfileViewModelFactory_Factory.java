package com.historymakers.resume.ui.profile.viewmodel;

import com.historymakers.resume.repository.ProfileRepository;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ProfileViewModelFactory_Factory implements Factory<ProfileViewModelFactory> {
  private final Provider<ProfileRepository> repositoryProvider;

  public ProfileViewModelFactory_Factory(Provider<ProfileRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ProfileViewModelFactory get() {
    return newInstance(repositoryProvider.get());
  }

  public static ProfileViewModelFactory_Factory create(
      Provider<ProfileRepository> repositoryProvider) {
    return new ProfileViewModelFactory_Factory(repositoryProvider);
  }

  public static ProfileViewModelFactory newInstance(ProfileRepository repository) {
    return new ProfileViewModelFactory(repository);
  }
}
