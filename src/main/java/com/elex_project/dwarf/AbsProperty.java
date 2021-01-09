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

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Base class for properties
 *
 * @param <T> type of a value
 * @author Elex
 */
@EqualsAndHashCode
abstract class AbsProperty<T> implements Property<T>, Serializable {

	private T value;
	@EqualsAndHashCode.Exclude
	private final ArrayList<PropertyListener<T>> listeners;

	/**
	 * A property with initial value 'null'
	 */
	public AbsProperty() {
		this(null);
	}

	/**
	 * A Property
	 *
	 * @param value initial value
	 */
	public AbsProperty(@Nullable T value) {
		this.listeners = new ArrayList<>();
		this.value = value;
	}

	@Override
	public String toString() {
		if (null == this.value) return "";
		return this.value.toString();
	}

	@Override
	public void setValue(@Nullable final T value) {
		if ((null == this.value && null == value)
				|| (null != this.value && this.value.equals(value))) return;
		final T oldValue = this.value;
		this.value = value;
		for (PropertyListener<T> listener : listeners) {
			listener.onValueChanged(oldValue, this.value);
		}
	}

	@Override
	@Nullable
	public T getValue() {
		return value;
	}

	@Override
	public void addListener(@NotNull final PropertyListener<T> listener) {
		this.listeners.add(listener);
	}

	@Override
	public void removeListener(@NotNull final PropertyListener<T> listener) {
		this.listeners.remove(listener);
	}

	@Override
	public void removeAllListeners() {
		this.listeners.clear();
	}
}
