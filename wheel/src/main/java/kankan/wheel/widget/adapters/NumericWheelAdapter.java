/*
 *  Copyright 2011 Yuri Kanivets
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package kankan.wheel.widget.adapters;

import android.content.Context;

/**
 * Numeric Wheel adapter.
 */
public class NumericWheelAdapter extends AbstractWheelTextAdapter {
    
    /** The default min value */
    public static final int DEFAULT_MAX_VALUE = 9;

    /** The default max value */
    private static final int DEFAULT_MIN_VALUE = 0;
    
    /** Sort order */
    public enum SortOrder {
    	SMALLEST_AT_BOTTOM,
    	SMALLEST_AT_TOP
    }
    
    // Values
    private int minValue;
    private int maxValue;
    private SortOrder sortOrder = SortOrder.SMALLEST_AT_TOP;
    
    // format
    private String format;
    
    /**
     * Constructor
     * @param context the current context
     */
    public NumericWheelAdapter(Context context) {
        this(context, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     */
    public NumericWheelAdapter(Context context, int minValue, int maxValue) {
        this(context, minValue, maxValue, null, SortOrder.SMALLEST_AT_TOP);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     * @param sortOrder the sort order
     */
    public NumericWheelAdapter(Context context, int minValue, int maxValue, SortOrder sortOrder) {
        this(context, minValue, maxValue, null, sortOrder);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     * @param format the format string
     */
    public NumericWheelAdapter(Context context, int minValue, int maxValue, String format) {
    	this(context, minValue, maxValue, format, SortOrder.SMALLEST_AT_TOP);
    }

    /**
     * Constructor
     * @param context the current context
     * @param minValue the wheel min value
     * @param maxValue the wheel max value
     * @param format the format string
     * @param sortOrder the sort order
     */
    public NumericWheelAdapter(Context context, int minValue, int maxValue, String format, SortOrder sortOrder) {
        super(context);
        
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.format = format;
        this.sortOrder = sortOrder;
    }

    @Override
    public CharSequence getItemText(int index) {
        if (index >= 0 && index < getItemsCount()) {
        	int value;
        	if (sortOrder == SortOrder.SMALLEST_AT_TOP){
        		value = minValue + index;
        	} else {
        		value = maxValue - index;
        	}
            return format != null ? String.format(format, value) : Integer.toString(value);
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxValue - minValue + 1;
    }    
}
