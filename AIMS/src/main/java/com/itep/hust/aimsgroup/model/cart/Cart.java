package com.itep.hust.aimsgroup.model.cart;

import com.itep.hust.aimsgroup.model.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/*
 * SOLID ANALYSIS
 * SINGLE RESPONSIBILITY: This class only have 1 reason to change and that is functionality relating to Cart
 * OPEN CLOSE: To add functionality just add a method
 */

/*
 * This class violates common coupling
 * The whole application uses one single global instance of the Cart class
 */

/*
 * Analyze cohesion
 * Functional cohesion
 * All subcomponents of this component serves one purpose of working with the cart
 */

/**
 * Cart class represents the shopping cart for the application
 * This class is implemented using the Singleton pattern
 * Usage: Cart.getCart() to get the cart instance
 * @author tvkain
 */
public class Cart {
    private final static Cart cart = new Cart();
    private final ObservableMap<Media, Integer> medias = FXCollections.observableHashMap();

    /**
     * Get the cart object
     * @return              the global cart object
     * @author tvkain
     */
    public static synchronized Cart getInstance() {
        return cart;
    }

    /**
     * Singleton
     */
    private Cart() {

    }

    /**
     * Add media to cart with specified quantity
     * This method will create an entry in the map for the media if it's not in the map
     * If the media is already in the map i.e. there are some amount of that media in the cart
     * already, the method will increase the quantity of the cart with the specified quantity
     * @param media         the media to add to cart
     * @param quantity      the quantity of the specified media
     * @author tvkain
     */
    public void add(Media media, Integer quantity) {
        if (medias.containsKey(media)) {
            quantity += medias.get(media);
        }

        medias.put(media, quantity);
    }

    /**
     * Remove all the medias in cart
     * This method removes the entry of the media in the map
     * @param media     the media to be removed
     * @author tvkain
     */
    public void remove(Media media) {
        medias.remove(media);
    }

    /**
     * Get the number of items in the cart
     * The number of items is calculated as the total number of media in the cart
     * i.e. the total quantity value of the map
     * @return          the size of the cart
     */
    public Integer getSize() {
        int size = 0;

        for (Integer value : medias.values()) {
            size += value;
        }

        return size;
    }

    /**
     * Get the list of medias that support rush order in the cart
     * @return          the supported medias
     */
    public Map<Media, Integer> getRushSupportedMedias() {
        Map<Media, Integer> supportMedias = new HashMap<>();

        for (Map.Entry<Media, Integer> entry : medias.entrySet()) {
            if (entry.getKey().isRushDelivery()) {
                supportMedias.put(entry.getKey(), entry.getValue());
            }
        }

        return supportMedias;
    }

    /**
     * Get number of medias that support rush order in the cart
     * @return          the number of supported medias
     */
    public int getRushSupportedMediasCount() {
        Map<Media, Integer> supportMedias = getRushSupportedMedias();

        int count = 0;
        for (Map.Entry<Media, Integer> entry : supportMedias.entrySet()) {
            count += entry.getValue();
        }

        return count;
    }

    /**
     * Calculate the total price in the cart
     * @return          the total price
     */
    public Integer getTotalPrice() {
        int total = 0;
        for (Map.Entry<Media, Integer> entry : medias.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Get the quantity of the specified media in the cart
     * Return null.sqlite if the media is not in the cart
     * @param media     the media
     * @return          the quantity of the specified media
     */
    public Integer getQuantity(Media media) {
        return medias.get(media);
    }

    /**
     * Check if the media is in cart
     * @param media     the media
     * @return          true or false
     */
    public boolean isInCart(Media media) {
        return medias.containsKey(media);
    }

    /**
     * Return an unmodifiable view of the inner map media -> quantity
     * The map is unmodifiable to preserve encapsulation
     * @return          the unmodifiable view of the inner map
     */
    public Map<Media, Integer> getMedias() {
        return Collections.unmodifiableMap(medias);
    }

    /**
     * Check if the medias in cart has enough in stock to support for the quantity in cart
     * @return          true if enough, false otherwise
     */
    public boolean hasEnoughStock() {
        for (Map.Entry<Media, Integer> entry : medias.entrySet()) {
            if (entry.getValue() > entry.getKey().getQuantity()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the heaviest media in cart
     * @return          The heaviest entry in the cart
     */
    public Map.Entry<Media, Integer> getHeaviestMedia() {
        double heaviest = 0.;

        Map.Entry<Media, Integer> heaviestMedia = null;
        for (Map.Entry<Media, Integer> entry : medias.entrySet()) {
            double weight = entry.getKey().getWeight() * entry.getValue();

            if (weight > heaviest) {
                heaviest = weight;
                heaviestMedia = entry;
            }
        }

        return heaviestMedia;
    }

    /**
     * Empty the cart
     * @author tvkain
     */
    public void empty() {
        medias.clear();
    }

    /**
     * Add a listener when the map change
     * This method is mainly used for UI
     * @param mapChangeListener     The listener to be added
     */
    public void addChangeListener(MapChangeListener<? super Media, ? super Integer> mapChangeListener) {
        medias.addListener(mapChangeListener);
    }

    /**
     * Add a listener when the map change
     * This method is mainly used for UI
     * @param mapChangeListener     The listener to be removed
     */
    public void removeChangeListener(MapChangeListener<? super Media, ? super Integer> mapChangeListener) {
        medias.removeListener(mapChangeListener);
    }
}
