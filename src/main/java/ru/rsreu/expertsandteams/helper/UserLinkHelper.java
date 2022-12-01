package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.config.AuthConfig;
import ru.rsreu.expertsandteams.constant.RouteNames;
import ru.rsreu.expertsandteams.data.Link;
import ru.rsreu.expertsandteams.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserLinkHelper {
    private UserLinkHelper() {
    }

    public static List<String> getUserPages(User user) {
        List<String> pages = user.getRoles().stream()
                .map(role -> AuthConfig.getRoleUrlPatterns(role))
                .flatMap(targets -> targets.stream())
                .collect(Collectors.toList());

        Map<String, Boolean> pagesMap = pages.stream()
                .collect(Collectors.toMap(page -> page, page -> true, (a, b) -> b));

        List<String> pagesWithoutDuplicates = new ArrayList<>();

        pages.stream().filter(pagesMap::get).forEach(page -> {
            pagesWithoutDuplicates.add(page);
            pagesMap.put(page, false);
        });

        return pagesWithoutDuplicates;
    }

    public static List<Link> getUserLinks(List<String> pages) {
        return pages.stream()
                .map(target -> new Link(RouteNames.getRouteName(target), UrlHelper.getUrlWithPrefix(target)))
                .collect(Collectors.toList());
    }
}
