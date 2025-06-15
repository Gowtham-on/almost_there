import SwiftUI
import ComposeApp
import GoogleMaps
import FirebaseCore
import FirebaseMessaging
import Firebase


@main
struct iOSApp: App {
    init() {
        koinkt.initKoin()
        GMSServices.provideAPIKey("MAP_API_KEY")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
