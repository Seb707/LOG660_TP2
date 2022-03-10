import { useCallback } from "react";

function TextInput({ id, label, value, didChangeValue }) {
  const didChange = useCallback(
    (event) => {
      didChangeValue(event.target.value);
    },
    [didChangeValue]
  );

  return (
    <div>
      <label for={id}>{label}</label>
      <input id={id} value={value} onChange={didChange} />
    </div>
  );
}

export default TextInput;
