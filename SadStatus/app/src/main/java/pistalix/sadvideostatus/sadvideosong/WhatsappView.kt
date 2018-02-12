package pistalix.sadvideostatus.sadvideosong

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.johnpersano.supertoasts.library.Style
import com.github.johnpersano.supertoasts.library.SuperActivityToast
import kotlinx.android.synthetic.main.activity_whatsapp_view.*
import java.io.File
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils
import com.google.android.gms.ads.InterstitialAd


class WhatsappView : AppCompatActivity() {
    var videourl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_whatsapp_view)
        overridePendingTransition(R.xml.enter, R.xml.exit);
        videourl = intent.getStringExtra("videoid")
        video_view.setMediaController(media_controller)
        video_view.setVideoURI(Uri.parse(videourl))
        video_view.start()
        whatsapp.setOnClickListener{
            try{
                if(appInstalledOrNot("com.whatsapp")){

                    var set =videourl
                    share_video(set.toString(),"com.whatsapp")
                }else{

                    ToastInstallApp()
                }
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }


        }
        fb.setOnClickListener{
            try{
                if(appInstalledOrNot("com.facebook.katana")){
                    var set =videourl
                    share_video(set.toString(),"com.facebook.katana" +
                            "")
                }else{

                    ToastInstallApp()
                }
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }

        }

        insta.setOnClickListener{
            try{
                if(appInstalledOrNot("com.instagram.android")){
                    var set =videourl
                    share_video(set.toString(),"com.instagram.android")
                }else{

                    ToastInstallApp()
                }
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }

        }

        hike.setOnClickListener{
            try{
                if(appInstalledOrNot("com.bsb.hike")){
                    var set =videourl
                    share_video(set.toString(),"com.bsb.hike")
                }else{

                    ToastInstallApp()
                }
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }

        }

        fbmsg.setOnClickListener{
            try{
                if(appInstalledOrNot("com.facebook.orca")){
                    var set =videourl
                    share_video(set.toString(),"com.facebook.orca")
                }else{

                    ToastInstallApp()
                }
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }

        }

        main_share.setOnClickListener{
            try{
                var set =videourl
                mainshare(set.toString())
            }
            catch (e:NullPointerException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch (e:IllegalArgumentException){
                ToastMsgFail("Sorry Problem In share in your device")
            }catch(e:Exception){
                ToastMsgFail("Sorry Problem In share in your device")
            }

        }

        other_app.setOnClickListener{
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=Pistalix%20Software%20Solutions")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Pistalix%20Software%20Solutions")))
            }
        }
    }

    fun share_video(filepath:String,packeg:String) {
        var string_path = Uri.fromFile(File(filepath))
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "video/*"
        sharingIntent.`package` = packeg
        sharingIntent.putExtra(Intent.EXTRA_STREAM, string_path)

        try {
            startActivity(sharingIntent)
        } catch (e: android.content.ActivityNotFoundException) {

            ToastInstallApp()

        }
    }

    fun appInstalledOrNot(uri: String): Boolean {
        val pm = packageManager
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {

        }

        return false
    }

    fun mainshare(filename:String){

        var string_path =Uri.parse(filename)
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "video/*"
        intent.putExtra(Intent.EXTRA_STREAM, string_path)
        startActivity(Intent.createChooser(intent, "Share via"))
    }
    override fun onBackPressed(){
        overridePendingTransition(R.xml.nathing,R.xml.exit)
        finish()

    }

    fun ToastInstallApp(){

        SuperActivityToast.create(this).setText("First Install App...").setDuration(Style.DURATION_MEDIUM).setFrame(Style.FRAME_LOLLIPOP).setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED)).setAnimations(Style.ANIMATIONS_POP).show();
    }

    internal fun ToastMsgFail(str: String) {
        SuperActivityToast.create(this).setText(str).setDuration(Style.DURATION_MEDIUM).setFrame(Style.FRAME_KITKAT).setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED)).setAnimations(Style.ANIMATIONS_POP).show()
    }
}
