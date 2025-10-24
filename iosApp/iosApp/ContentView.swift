import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        let lifecycle = UtilsKt.createLifecycle()

        let controller = MainViewControllerKt.MainViewController(lifecycle: lifecycle)

        lifecycle.onCreate()
        lifecycle.onStart()
        lifecycle.onResume()

        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    static func dismantleUIViewController(_ uiViewController: UIViewController, coordinator: ()) {
        let lifecycle = UtilsKt.createLifecycle()
        lifecycle.onPause()
        lifecycle.onStop()
        lifecycle.onDestroy()
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea()
    }
}
