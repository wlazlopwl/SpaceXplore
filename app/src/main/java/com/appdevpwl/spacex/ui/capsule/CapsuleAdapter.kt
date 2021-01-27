package com.appdevpwl.spacex.ui.capsule

//import com.appdevpwl.spacex.util.convertUnixTime
//import com.appdevpwl.spacex.util.convertUnixTime
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.databinding.CapsuleSingleItemRvBinding


class CapsuleAdapter : RecyclerView.Adapter<CapsuleAdapter.ViewHolder>() {

    private var list: List<Capsule> = emptyList()

    class ViewHolder(val binding: CapsuleSingleItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        private val capsuleName: TextView = mView.capsule_serial_type
//        private val activeSince: TextView = mView.capsule_active_since
//
//        // private val capsuleStatus: ImageView = mView.capsule_status_icon
//        private val countMissions: TextView = mView.capsule_count_missions
//        private val capsuleStatusTV: TextView = mView.capsule_status_tv
//        private val countLandings: TextView = mView.capsule_count_landings
        fun bindView(capsule: Capsule) {
            binding.apply {
                binding.capsuleItem = capsule
            }
//            capsuleName.text = capsule.serial + " | " + capsule.type
//            activeSince.text = when (capsule.original_launch_unix) {
//                null -> "No data"
//                else -> convertUnixTime(capsule.original_launch_unix)
//            }
//            capsuleStatus.setImageResource(
//                when (capsule.status) {
//                    "retired" -> R.drawable.ic_remove_circle_red_24dp
//                    "active" -> R.drawable.ic_add_circle_green_24dp
////                    "destroyed" - >
//                    else -> R.drawable.ic_help_grey_24dp
//                }
//            )


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: CapsuleSingleItemRvBinding =
            DataBindingUtil.inflate(inflater, R.layout.capsule_single_item_rv, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CapsuleAdapter.ViewHolder, position: Int) {
        val capsule = list[position]
        holder.bindView(capsule)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addCapsuleList(list: List<Capsule>) {
        this.list = list
        notifyDataSetChanged()

    }


}