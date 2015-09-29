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

/**
 *
 * @author captainbowtie
 */
public class Member implements Serializable {
    private static final long serialVersionUID = 20150812;
    private final Integer UID;
    private String name;
    public Member(Integer i, String n){
        UID = i;
        name = n;
    }
    public Member(Integer i){
        UID = i;
        name = "";
    }
    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public Integer getUID(){
        return UID;
    }
    @Override
    public String toString(){
        return(UID.toString()+"-"+name);
    }
}
