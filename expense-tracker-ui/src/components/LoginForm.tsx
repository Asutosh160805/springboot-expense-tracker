import {useState} from "react";
import {Link} from "react-router-dom";
import {handleError, handleSuccess} from "../utils/utility.ts";
import {ToastContainer} from "react-toastify";

export function LoginForm() {
    const [formData, setFormData] = useState({
        email : "",
        password : ""
    });

    const [loading, setLoading] = useState(false);

    async function handleSubmit(e : any) {
        e.preventDefault();

        setLoading(true);

        try {

            if (!formData.email || !formData.password) {
                throw new Error('Email and Password are required');
            }



            const response = await fetch(
                "http://localhost:8080/auth/login",
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(formData)
                }
            );

            console.log(response);

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || "Login failed");
            }

            handleSuccess("Logged in successfully");

            console.log(data);
        } catch(error : any) {
            handleError(error.message);
        } finally {
            setLoading(false);
        }
    }

    function handleChange(e : any) {
        const {name, value} = e.target;
        setFormData(prev => ({
            ...prev,
            [name] : value
        }));
    }

    return (
        <div className='container'>
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>

                <div>
                    <label htmlFor='email'>Email</label>
                    <input
                        type='text'
                        name='email'
                        placeholder='Enter your email'
                        value={formData.email}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label htmlFor='password'>Password</label>
                    <input
                        type='password'
                        name='password'
                        placeholder='Password'
                        value={formData.password}
                        onChange={handleChange}
                    />
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Logging in..." : "Login"}
                </button>
                <br/>
                <span>
                    Do not have an account?
                    <Link to='/register'>Create account</Link>
                </span>
            </form>
            <ToastContainer />
        </div>
    );
}

export default LoginForm;