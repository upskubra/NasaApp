
# NASA App

An application that presents images and details from NASA's rover API and allows filtering.

![In-App screenshots](https://www.linkpicture.com/q/nasaapp_ill.png)

  
## Used Technologies

**Android:** Clean Architecture, MVVM, Hilt Dagger, Retrofit

  ## Run it

Clone the project

```bash
  git clone https://github.com/upskubra/NasaApp
```

Open in Android Studio and run.

### Desired features of the project

- Technologies that we want to be made mandatory in the projects and that we expect you to use:

        - API 
        https://api.nasa.gov/index.html#browseAPI
        Example URL
        https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&amp;api_key=DEMO_KEY&amp;page=1
        If you have a problem with DEMOKEY, you can create your own key by registering from the link.
        
        The above api returns images taken by rover vehicles sent by NASA;
        
        [✅] You can show the pictures in a CollectionView with pagination thanks to the API. (will be infinite scroll)
        [✅] There should be 3 tabs in one tabbar. Each Tab also includes Curiosity, Opportunity, Spirit tools. There must be photos of one of them.
        [✅] Filtering by camera by filtering with the filter button on the top right for each vehicle should be done.
        [✅] When one of the pictures is touched, a pop-up opens and if the picture is on the top in the pop-up, it is taken. 
        date, vehicle name, from which camera, vehicle mission status, vehicle launch date and landing dateinformation should be included.
        

- Plus:
        
        [✅] Following any architectural design is a plus. (mvc, mvp, mvvm, viper, clean arc,...)
        [✅] It is a plus to use tools such as LiveData/ ViewModel, Flow, RxJava, Coroutines from Arch Components for async operations.
        [✅] The use of dependency injection will be considered as a plus.
        [✅] Source code must be deployed to a public repo. (GitHub, bitbucket, ...)
        [✅] Using up-to-date, preferred and open source libraries is a plus.
        [❌] Unit test writing will be considered a plus.
## Support and Feedback

If you want to give any feedback, please contact me at devkubrayildirim@gmail.com or [Twitter](https://twitter.com/Kubradev).
