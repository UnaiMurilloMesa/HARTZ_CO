import React from 'react';

type ButtonType = 'principal' | 'secondary';

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant: ButtonType;
}

const Button: React.FC<ButtonProps> = ({ variant, children, ...rest }) => {

  return (
    <>
			<style>{`
				.btn {
					padding: 0.75rem;
					width: 100%;
					max-width: 300px;
					border-radius: 8px;
					font-weight: bold;
					cursor: pointer;
					margin: 1rem 0;
				}
				.btn:focus {
          outline: 2px solid #000;
          outline-offset: 2px;
        }
        .btn.principal {
          background-color: black;
          color: white;
          border: none;
        }
        .btn.secondary {
            background-color: white;
						color: black;
						border: 1px solid #000;
        }
			`}</style>

			<button className={`btn ${variant}`} {...rest}>
				{children}
			</button>
    </>
  );
};

export default Button;
