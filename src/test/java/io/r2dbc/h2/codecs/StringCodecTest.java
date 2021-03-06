/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.r2dbc.h2.codecs;

import org.h2.value.Value;
import org.h2.value.ValueNull;
import org.h2.value.ValueString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

final class StringCodecTest {

    @Test
    void decode() {
        assertThat(new StringCodec().decode(ValueString.get("test"), String.class))
            .isEqualTo("test");
    }

    @Test
    void doCanDecode() {
        StringCodec codec = new StringCodec();

        assertThat(codec.doCanDecode(Value.STRING)).isTrue();
        assertThat(codec.doCanDecode(Value.STRING_FIXED)).isTrue();
        assertThat(codec.doCanDecode(Value.STRING_IGNORECASE)).isTrue();
        assertThat(codec.doCanDecode(Value.UNKNOWN)).isFalse();
        assertThat(codec.doCanDecode(Value.INT)).isFalse();
    }

    @Test
    void doEncode() {
        String string = "test";

        assertThat(new StringCodec().doEncode(string))
            .isEqualTo(ValueString.get("test"));
    }

    @Test
    void doEncodeNoValue() {
        assertThatIllegalArgumentException().isThrownBy(() -> new StringCodec().doEncode(null))
            .withMessage("value must not be null");
    }

    @Test
    void encodeNull() {
        assertThat(new StringCodec().encodeNull())
            .isEqualTo(ValueNull.INSTANCE);
    }
}
