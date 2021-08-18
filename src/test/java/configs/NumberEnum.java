package configs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberEnum {
    M(1000000),
    B(1000000000);

    private float value;
}
