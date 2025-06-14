import SwiftUI
import ComposeApp
import GoogleMaps

@main
struct iOSApp: App {

    init() {
        KoinKt.doInitKoin()
        GMSService.provideAPIKey("MAP_API_KEY")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
