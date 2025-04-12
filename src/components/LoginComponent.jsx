import React, { useState } from "react";
import Button from "./Button";

function LoginComponent(props) {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div>
      <h1>Login Component</h1>
      <div>
        <label>User Name:</label>
        <input
          type="text"
          value={userName}
          onChange={(v) => setUserName(v.target.value)}
          placeholder="Username"
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(v) => setPassword(v.target.value)}
          placeholder="Password"
        />
      </div>
      <div>
        <Button label="LOGIN"  />
      </div>
      <div>
        <label>
          Don't you have any account? <Button label="Sign Up" auth={props.auth} />
        </label>
      </div>
    </div>

    // <div>
    //   {age % 2 == 0 ? (
    //     <div>
    //       {" "}
    //       <p>This is is even {age}</p>
    //     </div>
    //   ) : (
    //     <div>
    //       <p>This is is odd {age}</p>
    //     </div>
    //   )}
    //   <div>The incremented value {age}</div>
    //   <button onClick={increment}>Increment</button>
    // </div>
  );
}

export default LoginComponent;
