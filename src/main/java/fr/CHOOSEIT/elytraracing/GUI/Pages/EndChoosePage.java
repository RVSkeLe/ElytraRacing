/**
 * MIT License
 *
 * Copyright (c) 2023 CHOOSEIT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.CHOOSEIT.elytraracing.GUI.Pages;

import fr.CHOOSEIT.elytraracing.AdminGUIConfig;
import fr.CHOOSEIT.elytraracing.GUI.HeadDatabase;
import fr.CHOOSEIT.elytraracing.GUI.Modules.MultiPagesModule;
import fr.CHOOSEIT.elytraracing.GUI.Page;
import fr.CHOOSEIT.elytraracing.GUI.VisibleObject;
import fr.CHOOSEIT.elytraracing.GUI.VisibleObjects.IconObject;
import fr.CHOOSEIT.elytraracing.Main;
import fr.CHOOSEIT.elytraracing.mapsystem.Objects.End;
import fr.CHOOSEIT.elytraracing.mapsystem.Map;
import fr.CHOOSEIT.elytraracing.mapsystem.ObjectClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EndChoosePage extends Page implements MultiPagesModule {
    public static AdminGUIConfig agui = Main.adminGUIConfig;
    Map map;

    public EndChoosePage(Player viewer, Map map) {
        super(Main.adminGUIConfig.c(Main.adminGUIConfig.pageECP), viewer, 5);
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void setup() {

    }

    @Override
    public void View() {

    }


    @Override
    public Object getInformation() {
        return null;
    }

    int pageNumber;

    @Override
    public void displayPage(int page) {
        if (page == 0) {
            page = 1;
        }
        if (map == null) {
            return;
        }
        removeObjects();
        pageNumber = page;

        ArrayList<End> infos = new ArrayList<>(map.getEndsList());
        End end;
        int size = infos.size();
        IconObject vo;
        ItemStack paper = new ItemStack(Material.PAPER);

        int startpage = MultiPagesModule.getIDPageStart(page, 36);
        int index = 0;

        List<String> l;

        for (int i = 0; i < 36; i++) {
            index = i + startpage;
            if (index >= size) {
                break;
            }

            vo = new IconObject(this, "SELECT", i, 1, 1);
            end = infos.get(index);
            vo.addItem(paper);
            vo.setInformation(end);
            vo.setDisplayName("§f" + agui.c(agui.id) + ": " + index);
            l = new ArrayList<>();
            l.add("§f» §7" + agui.c(agui.id) + ": §6" + end.getID(map.getEndsList()));
            l.add(" ");
            l.add("§f» §7" + agui.c(agui.particles) + ": " + "§f» §7" + agui.c(agui.normal) + ": §a" + ObjectClass.particleAsString(end.getEnumParticle()) + " §f«» §7" + agui.c(agui.ifnext) + ": §a" + ObjectClass.particleAsString(end.getEnumParticle_IFNEXT()));
            l.add("§f» §7" + agui.c(agui.particleAmount) + ": §a" + end.getParticleAmount());
            l.add(" ");
            l.add("§f» §7" + agui.c(agui.world) + ": §6" + end.getWorld());
            l.add("§f» §7x: §6" + end.getLocation().getX());
            l.add("§f» §7y: §6" + end.getLocation().getY());
            l.add("§f» §7z: §6" + end.getLocation().getZ());
            l.add(" ");
            l.add("§f» §7" + agui.c(agui.rotations) + ": " + "§f» §7x: §c" + end.getXDegrees() + " §f«» §7y: §c" + end.getYDegrees() + " §f«» §7z: §c" + end.getZDegrees());
            l.add(" ");
            l.add("§f» §7" + agui.c(agui.shape) + ": §6" + end.getShapeStr());
            l.add("§f» §7" + agui.c(agui.radius) + ": §6" + end.getRadiusSize());
            l.add(" ");
            l.add("§f" + agui.c(agui.leftConfigure) + "");
            l.add("§f" + agui.c(agui.rightTeleported) + "");
            vo.setDescription(l);
            vo.addDisplay();
        }
        if (index < size - 1) {
            vo = new IconObject(this, "PAGE_PLUS", 8, 4, 1, 1);
            vo.addItem(HeadDatabase.BLUE_RIGHT_ARROW);
            vo.setDisplayName(Main.cmc().basicsettingnoprefix(Main.cmc().CHANGEPAGERIGHT, null));
            vo.setDescription(Arrays.asList("§7" + agui.c(agui.clickChangePage) + ""));
            vo.addDisplay();
        }

        vo = new IconObject(this, "PAGE_MINUS", 0, 4, 1, 1);
        vo.addItem(HeadDatabase.RED_LEFT_ARROW);
        vo.setDisplayName(Main.cmc().basicsettingnoprefix(Main.cmc().CHANGEPAGELEFT, null));
        vo.setDescription(Arrays.asList("§7" + agui.c(agui.clickChangePage) + ""));
        vo.addDisplay();

        IconObject iconObject = new IconObject(this, "ADD", 4, 4);
        iconObject.setItem(HeadDatabase.GREEN_PLUS);
        iconObject.setDisplayName("§a" + agui.c(agui.createNew) + "");
        iconObject.setDescription(Arrays.asList("", "§f" + agui.c(agui.clickCreateNew) + "", "§f" + agui.c(agui.clickCreateNewEnd) + "", ""));
        iconObject.addDisplay();

    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public void click(VisibleObject visibleObject, ClickType clickType) {
        super.click(visibleObject, clickType);

        if (visibleObject.getId().equals("PAGE_PLUS")) {
            int currentpage = getPageNumber();
            displayPage(currentpage + 1);
        } else if (visibleObject.getId().equals("PAGE_MINUS")) {
            int currentpage = getPageNumber();
            if (currentpage == 1) {
                MapPage mapPage = new MapPage(getViewer(), map);
                mapPage.open();

                return;
            }
            displayPage(currentpage - 1);
        } else if (visibleObject.getId().equals("SELECT")) {
            End end = (End) visibleObject.getInformation();
            if (clickType.equals(ClickType.LEFT) || clickType.equals(ClickType.SHIFT_LEFT)) {
                EndPage endPage = new EndPage(getViewer(), map, end);
                endPage.setInformationObject("CHOOSE");
                endPage.open();
                endPage.View();
            } else {
                getViewer().teleport(end.getLocation());
                sendViewerMessage(Main.cmc().TELEPORTING);
                return;
            }
        } else if (visibleObject.getId().equals("ADD")) {
            Player player = getViewer();
            Location loc = player.getLocation();
            End end = new End((float) loc.getX(), (float) loc.getY(), (float) loc.getZ(), loc.getWorld().getName(), 3, 1, 0, 0, 0);
            map.addEnds(end);
            map.save();
            EndPage endPage = new EndPage(player, map, end);
            endPage.View();
            endPage.open();
        }
    }
}
