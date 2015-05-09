package ca.codemake.CubeBreak.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

import ca.codemake.CubeBreak.Constants;

/**
 * Created by Kajan on 5/8/2015.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    private AssetManager assetManager;

    // Assets
    public AssetTiles tiles;
    public AssetDividers dividers;

    // singleton: prevent instantiation from other classes
    private Assets() {}

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        // set asset manager error handler
        assetManager.setErrorListener(this);
        // load texture atlas
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECT, TextureAtlas.class);
        // start loading assets and wait until finished
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
        for (String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: " + a);

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECT);

        // Enable texture filtering for pixel smoothing
        for (Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        // Create game resource objects
        tiles = new AssetTiles(atlas);
        dividers = new AssetDividers(atlas);
    }

    public void error(String filename, Class type, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'" + (Exception)throwable);
    }

    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
    }

    public void dispose() {
        assetManager.dispose();
    }

    public class AssetTiles {
        public final AtlasRegion red;
        public final AtlasRegion green;
        public final AtlasRegion blue;
        public final AtlasRegion black;
        public final AtlasRegion select;
        public final AtlasRegion lightgrey;
        public final AtlasRegion darkgrey;

        public AssetTiles(TextureAtlas atlas) {
            red = atlas.findRegion("red");
            green = atlas.findRegion("green");
            blue = atlas.findRegion("blue");
            black = atlas.findRegion("black");
            lightgrey = atlas.findRegion("lightgrey");
            darkgrey = atlas.findRegion("darkgrey");
            select = atlas.findRegion("select");
        }
    }

    public class AssetDividers {
        public final AtlasRegion darkbar16;
        public final AtlasRegion lightbar16;

        public AssetDividers(TextureAtlas atlas) {
            darkbar16 = atlas.findRegion("darkbar16");
            lightbar16 = atlas.findRegion("lightbar16");
        }
    }
}
