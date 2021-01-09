/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2021, Elex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.elex_project.dwarf;

import org.jetbrains.annotations.Nullable;

/**
 * Number property
 *
 * @param <T> number
 */
abstract class NumberProperty<T extends Number> extends AbsProperty<T> {

	/**
	 * Number property
	 *
	 * @param value initial value
	 */
	public NumberProperty(@Nullable final T value) {
		super(value);
	}

	@Override
	public String toString() {
		return (null == getValue()) ? EMPTY_STRING : String.valueOf(getValue());
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof Number) {
			return o.equals(this.getValue());
		} else if (o instanceof NumberProperty
				&& null != ((NumberProperty<?>) o).getValue()) {
			return ((NumberProperty<?>) o).getValue().equals(this.getValue());
		} else {
			return super.equals(o);

		}
	}

	@Override
	protected boolean canEqual(final Object other) {
		if (other instanceof Number || other instanceof Property) {
			return true;
		} else {
			return super.canEqual(other);
		}
	}
}
