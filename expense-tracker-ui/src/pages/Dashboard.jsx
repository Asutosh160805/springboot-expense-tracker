import Sidebar from "../components/Sidebar";
import DashboardCard from "../components/DashboardCard";

function Dashboard() {

    return (

        <div>

            <Sidebar />

            <main>

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

            </main>

        </div>

    );

}

export default Dashboard;