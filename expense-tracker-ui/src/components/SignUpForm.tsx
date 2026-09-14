import {useState} from "react";
import {Link, useNavigate} from 'react-router-dom';
import {ToastContainer} from "react-toastify";
import {handleError, handleSuccess} from "../utils/utility.ts";

export function SignUpForm() {
    const [formData, setFormData] = useState({
        Name : "",
        Email : "",
        Password : "",
        ConfirmPassword : "",
    });

    const navigate = useNavigate();

    const [loading, setLoading] = useState(false);

    async function handleSubmit(e : any) {
        e.preventDefault();

        setLoading(true);

        try {

            const userDetails = {
                name : formData.Name,
                email : formData.Email,
                password : formData.Password
            };

            if (!userDetails.name || !userDetails.email || !userDetails.password) {
                throw new Error('Name, Email, and Password are required');
            }

            const response = await fetch(
                "http://localhost:8080/auth/register",
                {
                    method: 'POST',
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(userDetails)
                }
            );

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || "Sign up failed");
            }

            handleSuccess("Account created successfully");

            setTimeout(() => {
                navigate('/login', {replace : true})
            }, 1000);

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
            <h1>Sign Up</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor='name'>Name</label>
                    <input
                        type='text'
                        name='Name'
                        autoFocus
                        placeholder='Enter your name'
                        value={formData.Name}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label htmlFor='email'>Email</label>
                    <input
                        type='text'
                        name='Email'
                        placeholder='Enter your email'
                        value={formData.Email}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label htmlFor='password'>Password</label>
                    <input
                        type='password'
                        name='Password'
                        placeholder='Password'
                        value={formData.Password}
                        onChange={handleChange}
                    />
                </div>

                <div>
                    <label htmlFor='confirmPassword'>Confirm Password</label>
                    <input
                        type='password'
                        name='ConfirmPassword'
                        placeholder='Re-enter password'
                        value={formData.ConfirmPassword}
                        onChange={handleChange}
                    />
                </div>

                {
                    formData.ConfirmPassword &&
                    formData.Password !== formData.ConfirmPassword &&
                    (<p>Passwords do not match</p>)
                }

                <button type="submit" disabled={loading}>
                    {loading ? "Creating user..." : "Sign Up"}
                </button>
                <br/>
                <span>
                    Already have an account ?  <Link to='/login'>Login</Link>
                </span>

            </form>
            <ToastContainer />
        </div>
    );
}

export default SignUpForm;