/*
 * Copyright (C) 2015 captainbowtie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.allenbarr.MockStats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author captainbowtie
 */
public class Season implements Serializable {
    private static final long serialVersionUID = 20150812;
    private List<Integer> members = new ArrayList<>();
    private List<Tournament> tournaments = new ArrayList<>();
}
