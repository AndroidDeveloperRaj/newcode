package mitpi.sadvideostatus.sadvideosong

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.json.JSONArray
import org.json.JSONObject


//https://i.ytimg.com/vi/I4wkxIUQi1g/default.jpg
class RecycleJsonSug (var name: JSONArray,var playlistId:String,var ads :InterstitialAd): RecyclerView.Adapter<RecycleJsonSug.ViewHolder>()
{
    lateinit var context1:Context
    var click_event = 0;
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        var json1:JSONObject
        json1 = name.getJSONObject(position)
        holder.title.text= json1.getString("title")
        var videoid =json1.getString("id")
        var imageurl=json1.getString("imageurl")


        ads.adListener = object : AdListener() {
            override fun onAdClosed() {
                val adRequest = AdRequest.Builder().addTestDevice(context1.getString(R.string.interstial_ads)).build()
                ads.loadAd(adRequest)
            }
        }
        holder.video.setOnClickListener {

            val intent = Intent(context1, MainVideoView::class.java)
            intent.putExtra("videoid", videoid)
            intent.putExtra("playlistId", playlistId)
            intent.putExtra("Title",json1.getString("title"))
            context1.startActivity(intent)
            ads.show()
            (context1 as Activity).finish()
        }

        var url = imageurl
//        appUtils.setImage(url, holder.thummail, 0, context1)
        Picasso.with(context1).load(url).fit().into(holder.thummail)

    }

    override fun getItemCount(): Int {
        return name.length()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleJsonSug.ViewHolder {

        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.suggetionvideo, parent, false)
        context1=parent.context
        return ViewHolder(itemView)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView = itemView.find<TextView>(R.id.title)
        val thummail:ImageView = itemView.find<ImageView>(R.id.imagethum)
        val video:CardView = itemView.find<CardView>(R.id.card_view2)



        init {

        }
    }
}
