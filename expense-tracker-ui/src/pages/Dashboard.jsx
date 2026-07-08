import Sidebar from "../components/Sidebar";
import DashboardCard from "../components/DashboardCard";
import "../styles/dashboard.css";

function Dashboard() {

    return (

        <div className="dashboard">

            <Sidebar />

            <main className="content">

                <div className="cards">

                    <DashboardCard
                        title="Monthly Budget"
                        value="₹5000"
                    />

                    <DashboardCard
                        title="Spent"
                        value="₹2500"
                    />

                    <DashboardCard
                        title="Remaining"
                        value="₹2500"
                    />

                </div>

                <section className="transactions">

                    <h2>Recent Transactions</h2>

                    <p>No transactions available.</p>

                </section>

            </main>

        </div>

    );

}

export default Dashboard;