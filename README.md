# DISCLAIMER #

This is a homework for an event "[Android Academy Moscow](https://habr.com/post/420573/), Android Fundamentals" 2018.

The project may contains wrong patterns, bad practice, code examples which are not corresponds with S.O.L.I.D. principles and/or with a Clean architecture.
Etc. Please don't tell Uncle Bob.
Also this may contains some piece of incomprehensible humor.
Please use the code "as is" and don't ask questons.

### What is this repository for? ###

* This is to demonstrate the result of my home work during training. The code may be used for a code review or as a code examples.
* Current version is a "1.5.0.6" and it means that homework completed at about 45%. Don't try to find any logic in numbers. It's just a revision number.
* [See the Original examples here](https://github.com/android-academy-msk)

### A rules i decided to realize in the project (a Contract) ###

* Content source for the application is a [NY Times Rest API](https://developer.nytimes.com/top_stories_v2.json#/Documentation/GET/%7Bsection%7D.%7Bformat%7D)
* Supports Portrait and Landscape orientations
* No DI. Application class holds and provides instances (#OldSchoolStyle, #HolyWar)
* No third-sides lifecycle frameworks
* As an exercise for the Lesson #9, i have to implement [MOXY framework](https://github.com/Arello-Mobile/Moxy), as a separate branch (TODO, not realized yet)
* View - Presenter - View logic. Presenter control Lifecycle via android LifecycleObserver/LifecycleOwner
* View can't observes data at the Presenter side. Only direct two-way commands via interfaces
* It means no bindings: @BindingAdapter, ViewModel properties etc.
* For an asynchronous work i use ReactiveX 2 (io.reactivex.rxjava2)
* For a networking i use Retrofit + OkHttp + Gson-converter for JSON
* For a persistence i use ROOM. Data providers in a data layer may observe data changes in the ROOM and notify Presenters
* For the architecture pattern i tried a variant of a Clean Architecture as offered by Eugen Matzuk, Kaspersky lab Team Lead  [github](https://github.com/matzuk/TestableCodeMobius), [youtube, RUS](https://www.youtube.com/watch?v=AlxMGxs2QnM)
- Project separates on layers. Each layer separates on features (a unit with completed logic) and common code.
- UI layer (views, list adapters, view holders, presenters, presenter mediator/manager, models with UI Objects)
- Business layer (interactor manager, interactor facades, interactors)
- Data layer (data manager, data source managers, network part with Api and DTO, database part with DAO, shared preferences part)
- Utils layer (contains an Application class and any utils)

### How do I get set up? ###

* Please read the Contract above
* Activity and Fragment views have to extend their base classes (BaseActivity / BaseFragment). Base classes:
- Help to register a LifecycleObserver
- Provide presenters
* View's feature interfaces (i.e. IDigestsView) have to implement IView interface which is used as a special mark. Also implements LifecycleOwner
* Presenters have to extend BasePresenter:
- Listen to the View's Livecycle events, handle lifecycle, handle CompositeDisposables during lifecycle
* Presenter's feature interfaces (i.e. IDigestsPresenter) have to implement IPresenter interface which is used as a special mark. Also implements LifeCycleObserver
* To realize View -> Presenter command, create an interface IFeatureView implements IView. Register an instance of the IFeatureView in the FeaturePresenter
* To realize Presenter -> View command, create an interface IFeaturePresenter implements IPresenter. Add a presenter to the PresenterManager
* Application class holds and provides all global instances (look at the Utils layer)
* Each data source in the Data layer corresponds with the personal FeatureInteractor in the Business layer. To aggregate interactors result and to provide it to the presenter use FeatureFacade
* Data sources are administrated by DataSourceManager. All these managers are agregated in the DataManager
* Models for Objects from:
- A network calls Data Transfer Objects (data/common/network/DTO)
- A data base calls Data Access Objects (data/common/db/DAO)
- A ui calls User Interface Objects (ui/common/UIO)

### Contribution guidelines ###

* This is a homework and i'm not waiting for any contributions
* You could do a code review on your own
* Sorry, no tests are available

### Who do I talk to? ###

* Original repo owner is a [Sergio](https://github.com/webanimal)
* You may try to find me @ android-academy-msk.slack.com, Sergio D