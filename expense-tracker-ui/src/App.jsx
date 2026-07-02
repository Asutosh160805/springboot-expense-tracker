import Header from "./components/Header";
import Footer from "./components/Footer";
import Dashboard from "./pages/Dashboard";

function App() {
    return (
        <div>

            <Header

                title="Expense Tracker"

                username="Asutosh"

            />


            <h2>Dashboard</h2>

            <Footer />

        </div>
    );
}

export default App;