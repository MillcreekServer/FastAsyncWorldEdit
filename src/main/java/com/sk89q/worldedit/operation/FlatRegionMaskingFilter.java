/*
 * WorldEdit, a Minecraft world manipulation toolkit
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldEdit team and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldedit.operation;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.masks.Mask2D;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Passes calls to {@link #apply(com.sk89q.worldedit.Vector2D)} to the
 * delegate {@link com.sk89q.worldedit.operation.FlatRegionFunction} if they
 * match the given mask.
 */
public class FlatRegionMaskingFilter implements FlatRegionFunction {

    private final EditSession editSession;
    private final FlatRegionFunction function;
    private Mask2D mask;

    /**
     * Create a new masking filter.
     *
     * @param editSession the edit session
     * @param mask the mask
     * @param function the delegate function to call
     */
    public FlatRegionMaskingFilter(EditSession editSession, Mask2D mask, FlatRegionFunction function) {
        checkNotNull(function);
        checkNotNull(editSession);
        checkNotNull(mask);

        this.editSession = editSession;
        this.mask = mask;
        this.function = function;
    }

    @Override
    public boolean apply(Vector2D position) throws WorldEditException {
        return mask.matches(editSession, position) && function.apply(position);
    }

}
