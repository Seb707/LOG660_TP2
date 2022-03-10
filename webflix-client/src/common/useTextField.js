import { useState, useCallback } from "react";

function useTextField(defaultValue) {
  const [value, setValue] = useState(defaultValue);
  const didChangeValue = useCallback(
    (event) => {
      setValue(event.target.value);
    },
    [setValue]
  );

  return [value, didChangeValue];
}

export default useTextField;
