package ru.app.rz0.vyngtestapp.features.videolist;

import javax.inject.Inject;
import ru.app.rz0.vyngtestapp.base.presentation.BasePresenter;

public class VideoListPresenter extends BasePresenter<VideoListView> {

  private VideoListInteractor interactor;

  @Inject
  public VideoListPresenter(VideoListInteractor interactor) {
    this.interactor = interactor;
  }

  public void searchStarted(String query) {
    addSubscription(
        interactor.search(query)
            .doOnSubscribe(disposable -> getView().setLoading(true))
            .doFinally(() -> getView().setLoading(false))
            .subscribe(items -> getView().onItemsLoaded(items), Throwable::printStackTrace));
  }
}
