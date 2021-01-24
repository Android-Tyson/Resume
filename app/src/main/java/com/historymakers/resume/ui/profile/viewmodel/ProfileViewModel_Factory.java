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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<ProfileRepository> repositoryProvider;

  public ProfileViewModel_Factory(Provider<ProfileRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<ProfileRepository> repositoryProvider) {
    return new ProfileViewModel_Factory(repositoryProvider);
  }

  public static ProfileViewModel newInstance(ProfileRepository repository) {
    return new ProfileViewModel(repository);
  }
}
