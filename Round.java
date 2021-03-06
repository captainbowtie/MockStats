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
public class Round implements Serializable {
    private static final long serialVersionUID = 20150812;
    private Boolean isPlaintiff = true;
    private Integer[] witnesses = {0,0,0,0,0,0};
    private Integer openAtty = 0;
    private Integer[] pAttys = {0,0,0};
    private Integer[] dAttys = {0,0,0};
    private Integer[] wits = {0,0,0};
    private Integer closeAtty = 0;
    private final List<Ballot> ballots = new ArrayList<>();
}
